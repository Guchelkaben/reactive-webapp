package io.cometenv.marv.reactive.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
public class PokemonCreatedEventPublisher implements ApplicationListener<PokemonCreatedEvent>, Consumer<FluxSink<PokemonCreatedEvent>> {

    private final Executor executor;
    private final BlockingQueue<PokemonCreatedEvent> queue = new LinkedBlockingQueue<>();

    PokemonCreatedEventPublisher(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void onApplicationEvent(PokemonCreatedEvent event) {
        this.queue.offer(event);
    }

    @Override
    public void accept(FluxSink<PokemonCreatedEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    PokemonCreatedEvent event = queue.take();
                    sink.next(event);
                } catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}