package br.com.taskmate.dto;

import br.com.taskmate.model.Work;

import java.util.UUID;

public class WorkResponse {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String location;
    private String workerUsername;

    public WorkResponse(Work work) {
        this.id = work.getId();
        this.name = work.getName();
        this.description = work.getDescription();
        this.price = work.getPrice();
        this.location = work.getLocation();
        this.workerUsername = (work.getWorker() != null) ? work.getWorker().getUsername() : null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkerUsername() {
        return workerUsername;
    }

    public void setWorkerUsername(String workerUsername) {
        this.workerUsername = workerUsername;
    }
}