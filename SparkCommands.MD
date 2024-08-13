
# Apache Spark Commands: Batch vs Real-Time Processing

## Batch Processing Commands

### 1. Spark Session
```java
SparkSession.builder().appName("AppName").getOrCreate();
```
- Initializes a Spark session, which is the entry point for batch processing.

### 2. Reading Data
```java
read.format("csv").load("path/to/data.csv");
read.json("path/to/data.json");
read.parquet("path/to/data.parquet");
read.jdbc(url, "table", properties);
```
- Reads data from various sources like CSV, JSON, Parquet, or JDBC.

### 3. Transformations
```java
select("column1", "column2");
filter("column > value");
groupBy("column").agg(count("column"));
withColumn("new_column", expr("existing_column * 2"));
join(dataset, "column");
```
- Common transformations like selecting, filtering, grouping, and joining.

### 4. Actions
```java
show();
collect();
count();
write.format("parquet").save("output/path");
cache();
```
- Actions that trigger execution like showing, collecting, counting, and writing data.

### 5. Caching and Persistence
```java
cache();
persist(StorageLevel.MEMORY_AND_DISK);
```
- Caches or persists data in memory and/or disk.

## Real-Time (Stream) Processing Commands

### 1. Spark Session and Streaming Context
```java
SparkSession.builder().appName("StreamingApp").getOrCreate();
```
- Initializes a Spark session for structured streaming.

### 2. Reading Streaming Data
```java
readStream.format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "topic")
    .load();

readStream.format("socket")
    .option("host", "localhost")
    .option("port", 9999)
    .load();

readStream.format("csv")
    .schema(schema)
    .option("path", "input/path")
    .load();
```
- Reads data from streaming sources like Kafka, sockets, or files.

### 3. Streaming Transformations
```java
selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
withWatermark("timestampColumn", "10 minutes");
groupBy(window(col("timestamp"), "10 minutes"))
    .agg(count("value"));
join(streamingDataset, "column", "inner");
```
- Transforms streaming data with selections, watermarking, time-based grouping, and joins.

### 4. Writing Streaming Data
```java
writeStream.format("console")
    .outputMode("append")
    .start();

writeStream.format("parquet")
    .option("path", "output/path")
    .start();

writeStream.format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("topic", "outputTopic")
    .start();
```
- Writes streaming data to various outputs like console, files, or Kafka.

### 5. Streaming Triggers
```java
trigger(Trigger.ProcessingTime("10 seconds"));
trigger(Trigger.Once());
```
- Configures triggers to process data at intervals or once.

### 6. Managing Streaming Queries
```java
StreamingQuery query = writeStream.format("console").start();
query.awaitTermination();
query.stop();
```
- Manages streaming queries with start, await, and stop operations.

## Key Differences in Commands

- **Batch processing** uses commands that operate on a fixed set of data (e.g., `read`, `filter`, `groupBy`, `write`).
- **Streaming processing** uses commands designed to handle continuous data streams (e.g., `readStream`, `writeStream`, `trigger`, `awaitTermination`).
