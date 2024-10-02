package br.com.taskmate.model;

import br.com.taskmate.model.user.Client;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @Column(name = "status", updatable = true, nullable = true, length = 30) //inProgress, finished, notInitialized, waiting
    private String status;

    @Column(name = "requisition", updatable = true, nullable = true, length = 200)
    private String requisition;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = "notInitialized";
        }
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequisition() {
        return requisition;
    }

    public void setRequisition(String requisition) {
        this.requisition = requisition;
    }
}