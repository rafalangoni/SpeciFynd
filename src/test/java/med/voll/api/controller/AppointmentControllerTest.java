package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentScheduleService;
import med.voll.api.domain.appointment.DataDetailAppointment;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DataScheduleAppointment> dataScheduleAppointmentJson;

    @Autowired
    private JacksonTester<DataDetailAppointment> dataDetailAppointmentJson;

    @MockBean
    private AppointmentScheduleService appointmentScheduleService;

    @Test
    @DisplayName("Must return http 400 when wrong data came into request")
    @WithMockUser //Bypass security
    void scheduleAppointment1() throws Exception {
        var response = mockMvc.perform(post("/appointment"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return http 200 when valid request data")
    @WithMockUser //Bypass security
    void scheduleAppointment2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var dataDetail = new DataDetailAppointment(null, 2l, 5l, date);

        //Using Mockito
        when(appointmentScheduleService.schedule(any()))
                .thenReturn(dataDetail);

        var response = mockMvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataScheduleAppointmentJson.write(new DataScheduleAppointment(2l, 5l, date, specialty))
                                        .getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var responseJson = dataDetailAppointmentJson.write(dataDetail).getJson();

        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }
}