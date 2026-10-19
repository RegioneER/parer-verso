/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */
package it.eng.parer.simparer.security;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import net.sourceforge.stripes.controller.FileUploadLimitExceededException;
import net.sourceforge.stripes.exception.StripesRuntimeException;

/**
 * Verifica che {@link VersoExceptionHandler#handleGeneric} non esegua piu' sia {@code forward()}
 * che {@code sendError()} sulla stessa response (bug che generava UT010019 "Response already
 * committed" e log ripetuti che hanno saturato il disco in produzione), e che il controllo
 * {@code response.isCommitted()} venga rispettato prima di qualunque operazione sulla response.
 */
class VersoExceptionHandlerTest {

    private VersoExceptionHandler handler;
    private Logger mockLog;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() throws Exception {
        handler = new VersoExceptionHandler();

        // Sostituisce il Logger reale (campo privato final "log") con un mock, cosi'
        // possiamo contare esattamente quante volte viene scritta una riga di log,
        // senza dipendere da un binding SLF4J concreto.
        mockLog = mock(Logger.class);
        Field logField = VersoExceptionHandler.class.getDeclaredField("log");
        logField.setAccessible(true);
        logField.set(handler, mockLog);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/pages/error.jsp")).thenReturn(dispatcher);
    }

    @Test
    void handleGeneric_committedDuringForward_nonChiamaSendErrorELoggaUnaVoltaSola()
            throws Exception {
        // Scenario del bug di produzione: la response NON e' committed all'ingresso del
        // metodo, ma lo diventa a causa del forward() stesso (come farebbe un vero
        // RequestDispatcher che scrive/flush-a la pagina di errore). Con il vecchio codice,
        // la successiva chiamata a sendError() sulla response gia' committata generava
        // UT010019, catturata dal catch generico e rilogata ad ogni richiesta ricevuta.
        when(response.isCommitted()).thenReturn(false);
        doAnswer(invocation -> {
            when(response.isCommitted()).thenReturn(true);
            return null;
        }).when(dispatcher).forward(request, response);

        handler.handleGeneric(new RuntimeException("errore di test"), request, response);

        verify(dispatcher, times(1)).forward(request, response);
        // La correzione elimina la doppia chiamata: sendError() non deve mai essere invocato.
        verify(response, never()).sendError(anyInt(), anyString());
        verify(mockLog, times(1)).error(anyString(), any(Throwable.class));
        verifyNoMoreInteractions(mockLog);
    }

    @Test
    void handleGeneric_responseGiaCommittata_saltaForwardELoggaUnAvviso() throws Exception {
        when(response.isCommitted()).thenReturn(true);

        handler.handleGeneric(new RuntimeException("errore di test"), request, response);

        verify(dispatcher, never()).forward(any(), any());
        verify(response, never()).sendError(anyInt(), anyString());
        verify(mockLog, times(1)).error(anyString(), any(Throwable.class));
        verify(mockLog, times(1)).warn(anyString());
        verifyNoMoreInteractions(mockLog);
    }

    @Test
    void handleGeneric_chiamateRipetute_nonProduceMaiPiuDiUnLogPerChiamata() throws Exception {
        // Simula un container che re-invoca l'exception handler piu' volte (come
        // accaduto in produzione): ogni singola invocazione deve produrre sempre e solo
        // un log di errore, mai un ciclo di log all'interno della stessa invocazione.
        when(response.isCommitted()).thenReturn(true);

        int invocazioni = 5;
        for (int i = 0; i < invocazioni; i++) {
            handler.handleGeneric(new RuntimeException("errore " + i), request, response);
        }

        verify(mockLog, times(invocazioni)).error(anyString(), any(Throwable.class));
        verify(mockLog, times(invocazioni)).warn(anyString());
        verify(dispatcher, never()).forward(any(), any());
        verify(response, never()).sendError(anyInt(), anyString());
    }

    /** Rende stateful gli attributi della request mock, come in una richiesta reale. */
    private void abilitaAttributiRequest() {
        Map<String, Object> attributi = new HashMap<>();
        doAnswer(inv -> attributi.put(inv.getArgument(0), inv.getArgument(1))).when(request)
                .setAttribute(anyString(), any());
        when(request.getAttribute(anyString()))
                .thenAnswer(inv -> attributi.get(inv.<String>getArgument(0)));
    }

    @Test
    void handleGeneric_erroreDuranteGestioneErrore_interrompeLaCatenaSenzaForwardNeStackTrace()
            throws Exception {
        // Scenario error loop: la pagina di errore fallisce a sua volta e il container
        // re-invoca l'handler sulla stessa richiesta (dispatcher ERROR).
        abilitaAttributiRequest();
        when(response.isCommitted()).thenReturn(false);
        doAnswer(invocation -> {
            handler.handleGeneric(new RuntimeException("errore nella pagina di errore"), request,
                    response);
            return null;
        }).when(dispatcher).forward(request, response);

        handler.handleGeneric(new RuntimeException("errore iniziale"), request, response);

        verify(dispatcher, times(1)).forward(request, response);
        verify(mockLog, times(1)).error(anyString(), any(Throwable.class));
        verify(mockLog, times(1)).warn(anyString(), anyString());
        verify(response, times(1)).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    void handleDatabaseException_rientro_nonRieseguePiuForward() throws Exception {
        abilitaAttributiRequest();
        when(response.isCommitted()).thenReturn(false);

        handler.handleDatabaseException(new StripesRuntimeException("db"), request, response);
        handler.handleDatabaseException(new StripesRuntimeException("db"), request, response);

        verify(dispatcher, times(1)).forward(request, response);
        verify(mockLog, times(1)).error(anyString(), any(Throwable.class));
        verify(mockLog, times(1)).warn(anyString(), anyString());
    }

    @Test
    void handleFileUploadLimitExceeded_rientro_loggaUnaVoltaSola() throws Exception {
        abilitaAttributiRequest();
        when(response.isCommitted()).thenReturn(false);
        FileUploadLimitExceededException exc = new FileUploadLimitExceededException(1L, 2L);

        handler.handleFileUploadLimitExceeded(exc, request, response);
        handler.handleFileUploadLimitExceeded(exc, request, response);

        verify(mockLog, times(1)).error(anyString(), any(Throwable.class));
        verify(mockLog, times(1)).warn(anyString(), anyString());
    }

}
