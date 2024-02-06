package com.vet.appointment.system.appointment.service.dataaccess.appointment.mapper;

import com.vet.appointment.system.appointment.service.dataaccess.appointment.entity.AppointmentEntity;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataAccessMapper {

    public AppointmentEntity appointmentToAppointmentEntity(Appointment appointment) {
        return AppointmentEntity.builder()
                .id(appointment.getId().getValue())
                .ownerId(appointment.getOwnerId())
                .petId(appointment.getPetId())
                .description(appointment.getDescription())
                .appointmentStartDateTime(appointment.getAppointmentStartDateTime())
                .appointmentEndDateTime(appointment.getAppointmentEndDateTime())
                .build();
    }

    public Appointment appointmentEntityToAppointment(AppointmentEntity appointmentEntity) {
        return Appointment.builder()
                .id(appointmentEntity.getId())
                .ownerId(appointmentEntity.getOwnerId())
                .petId(appointmentEntity.getPetId())
                .description(appointmentEntity.getDescription())
                .appointmentStartDateTime(appointmentEntity.getAppointmentStartDateTime())
                .appointmentEndDateTime(appointmentEntity.getAppointmentEndDateTime())
                .build();
    }
}