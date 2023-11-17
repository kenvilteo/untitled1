package com.company.untitled1.listener;

import com.company.untitled1.entity.Dog;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DogEventListener {

    @EventListener
    public void onDogLoading(final EntityLoadingEvent<Dog> event) {

    }

    @EventListener
    public void onDogSaving(final EntitySavingEvent<Dog> event) {
        
    }

    @EventListener
    public void onDogChangedBeforeCommit(final EntityChangedEvent<Dog> event) {

    }

    @TransactionalEventListener
    public void onDogChangedAfterCommit(final EntityChangedEvent<Dog> event) {

    }
}