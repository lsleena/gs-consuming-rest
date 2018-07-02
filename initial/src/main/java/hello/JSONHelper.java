package hello;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONHelper {
    public static void main(String[] args) throws IOException{
        String singleRecord ="{\"row\":{\"columns\":[1527385895578,\"1\",1527385895320,\"User_6\",\"Page_21\"]},\"errorMessage\":null}";
        ObjectMapper objectMapper = new ObjectMapper();
        RequestRow firstRecord = objectMapper.readValue(singleRecord, RequestRow.class);

        System.out.println("Parsed record " + firstRecord);

        String secondLine = "{\"row\":null,\"errorMessage\":{\"message\":\"LIMIT reached for the partition.\",\"stackTrace\":[\"io.confluent.ksql.structured.QueuedSchemaKStream$QueuePopulator.apply(QueuedSchemaKStream.java:184)\",\"io.confluent.ksql.structured.QueuedSchemaKStream$QueuePopulator.apply(QueuedSchemaKStream.java:161)\",\"org.apache.kafka.streams.kstream.internals.KStreamPeek$KStreamPeekProcessor.process(KStreamPeek.java:42)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode$1.run(ProcessorNode.java:46)\",\"org.apache.kafka.streams.processor.internals.StreamsMetricsImpl.measureLatencyNs(StreamsMetricsImpl.java:208)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode.process(ProcessorNode.java:124)\",\"org.apache.kafka.streams.processor.internals.AbstractProcessorContext.forward(AbstractProcessorContext.java:174)\",\"org.apache.kafka.streams.kstream.internals.KStreamMapValues$KStreamMapProcessor.process(KStreamMapValues.java:41)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode$1.run(ProcessorNode.java:46)\",\"org.apache.kafka.streams.processor.internals.StreamsMetricsImpl.measureLatencyNs(StreamsMetricsImpl.java:208)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode.process(ProcessorNode.java:124)\",\"org.apache.kafka.streams.processor.internals.AbstractProcessorContext.forward(AbstractProcessorContext.java:174)\",\"org.apache.kafka.streams.kstream.internals.KStreamTransformValues$KStreamTransformValuesProcessor.process(KStreamTransformValues.java:169)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode$1.run(ProcessorNode.java:46)\",\"org.apache.kafka.streams.processor.internals.StreamsMetricsImpl.measureLatencyNs(StreamsMetricsImpl.java:208)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode.process(ProcessorNode.java:124)\",\"org.apache.kafka.streams.processor.internals.AbstractProcessorContext.forward(AbstractProcessorContext.java:174)\",\"org.apache.kafka.streams.kstream.internals.KStreamMapValues$KStreamMapProcessor.process(KStreamMapValues.java:41)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode$1.run(ProcessorNode.java:46)\",\"org.apache.kafka.streams.processor.internals.StreamsMetricsImpl.measureLatencyNs(StreamsMetricsImpl.java:208)\",\"org.apache.kafka.streams.processor.internals.ProcessorNode.process(ProcessorNode.java:124)\",\"org.apache.kafka.streams.processor.internals.AbstractProcessorContext.forward(AbstractProcessorContext.java:174)\",\"org.apache.kafka.streams.processor.internals.SourceNode.process(SourceNode.java:80)\",\"org.apache.kafka.streams.processor.internals.StreamTask.process(StreamTask.java:224)\",\"org.apache.kafka.streams.processor.internals.AssignedStreamsTasks.process(AssignedStreamsTasks.java:94)\",\"org.apache.kafka.streams.processor.internals.TaskManager.process(TaskManager.java:411)\",\"org.apache.kafka.streams.processor.internals.StreamThread.processAndMaybeCommit(StreamThread.java:923)\",\"org.apache.kafka.streams.processor.internals.StreamThread.runOnce(StreamThread.java:803)\",\"org.apache.kafka.streams.processor.internals.StreamThread.runLoop(StreamThread.java:750)\",\"org.apache.kafka.streams.processor.internals.StreamThread.run(StreamThread.java:720)\"]}}";
        RequestRow secondRecord = objectMapper.readValue(secondLine, RequestRow.class);

        System.out.println("Parsed record " + secondRecord);
    }
}
