package com.vet.appointment.system.appointment.service.domain.dto.get;

import com.vet.appointment.system.domain.dto.ResponseMessage;
import com.vet.appointment.system.domain.valueobject.AppointmentStatus;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class GetAppointmentResponse extends ResponseMessage {

    private UUID id;
    private UUID ownerId;
    private UUID petId;
    private String description;
    private LocalDateTime appointmentStartDateTime;
    private LocalDateTime appointmentEndDateTime;
    private AppointmentStatus appointmentStatus;
    private PaymentStatus paymentStatus;

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public UUID getPetId() {
        return petId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    private GetAppointmentResponse(Builder builder) {
        super(builder.message, builder.statusCode);
        id = builder.id;
        ownerId = builder.ownerId;
        petId = builder.petId;
        description = builder.description;
        appointmentStartDateTime = builder.appointmentStartDateTime;
        appointmentEndDateTime = builder.appointmentEndDateTime;
        appointmentStatus = builder.appointmentStatus;
        paymentStatus = builder.paymentStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private UUID id;
        private UUID ownerId;
        private UUID petId;
        private String description;
        private LocalDateTime appointmentStartDateTime;
        private LocalDateTime appointmentEndDateTime;
        private AppointmentStatus appointmentStatus;
        private PaymentStatus paymentStatus;
        private String message;
        private int statusCode;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder ownerId(UUID val) {
            ownerId = val;
            return this;
        }

        public Builder petId(UUID val) {
            petId = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder appointmentStartDateTime(LocalDateTime val) {
            appointmentStartDateTime = val;
            return this;
        }

        public Builder appointmentEndDateTime(LocalDateTime val) {
            appointmentEndDateTime = val;
            return this;
        }

        public Builder appointmentStatus(AppointmentStatus val) {
            appointmentStatus = val;
            return this;
        }

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder statusCode(int val) {
            statusCode = val;
            return this;
        }

        public GetAppointmentResponse build() {
            return new GetAppointmentResponse(this);
        }
    }
}