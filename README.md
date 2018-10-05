# Spike JanusDB graph database

http://janusgraph.org/
http://tinkerpop.apache.org/docs/current/reference/

JanusDB allows to use multiple backends(cassandra, bigquery etc) and ES for indexing.
It uses the tinkerpop/gremlin specs for querying/ingestion.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites + Installation

```
Java 8
Groovy
Python
Download version janusgraph-0.3.0-hadoop2 from https://github.com/JanusGraph/janusgraph/releases
```

### Start the server

```
./bin/janusgraph.sh
```

### Run the groovy script

```
./bin/gremlin.sh -e create_graph.groovy
```

### Run the python script
```
```

### Run the ruby script
```
```

