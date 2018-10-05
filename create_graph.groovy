import org.apache.tinkerpop.gremlin.process.traversal.*
import org.apache.tinkerpop.gremlin.tinkergraph.structure.*

org.janusgraph.graphdb.configuration.GraphDatabaseConfiguration.ALLOW_SETTING_VERTEX_ID = true

tg = JanusGraphFactory.open('janusgraph-cassandra-es.properties')

//TitanFactory.Builder config = TitanFactory.build();
//config.set("storage.backend", "berkeleyje");
//config.set("storage.directory", "C:/data/titan");
//config.set("graph.set-vertex-id", true);

//JanusGraphFactory.drop(tg) //https://stackoverflow.com/questions/12814305/gremlin-remove-all-vertex

//tg.io(graphson()).readGraph("tinkerpop-modern.json")
//tg.io(graphson()).readGraph("sample_gson.json")


def buildEdges(user, apps, categories, device, country, city) {
    user.addEdge("installed_on_device", device)
    device.addEdge("installed_device_by_user", user)

    for(app in apps){
        user.addEdge("installed_app", app)
        app.addEdge("installed_app_by_user", user)
    }

    for(category in categories){
        user.addEdge("in_category", category)
        category.addEdge("installed_app_by_category", user)
    }

    user1.addEdge("lives_in_country", country)
    user1.addEdge("lives_in_city", city)
}

user1 = tg.addVertex(T.label, "user", T.id, "u1", "platform", "gp")
app1 = tg.addVertex(T.label, "app", T.id, "com.foo.bar")
app2 = tg.addVertex(T.label, "app", T.id, 56234)
deviceRedmi = tg.addVertex(T.label, "device", T.id, "Redmi Note 4")
country = tg.addVertex(T.label, "country", T.id, "IN", "code", "IN")
city = tg.addVertex(T.label, "city", T.id, "mumbai", "code", "mumbai")

buildEdges(user1, [app1, app2], [], deviceRedmi, country, city)

tg.io(graphson()).writeGraph("sample_gson_with_ids.json")
edges = g.E().count().next()
verts = g.V().count().next()
println "The graph has $verts vertices"
println "The graph has $edges edges"
