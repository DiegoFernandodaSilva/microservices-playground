package com.github.diegofernandodasilva.dummyprocessorengine;

import com.github.diegofernandodasilva.dummyprocessorengine.kafka.producer.SampleProducer;
import com.github.diegofernandodasilva.microservices.playground.Sample;
import com.github.diegofernandodasilva.microservices.playground.typeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DummyProcessorEngineApplication implements CommandLineRunner {

    @Autowired
    private SampleProducer sampleProducer;

    public static void main(String[] args) {
        SpringApplication.run(DummyProcessorEngineApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            sampleProducer.produceSample(sample(i));
        }
    }

    private Sample sample(int id) {
        return Sample.newBuilder()
                .setId(String.valueOf(id))
                .setMessage("Message number " + id)
                .setType(id % 2 == 0 ? typeMsg.TYPE_TWO : typeMsg.TYPE_ONE)
                .build();
    }
}
