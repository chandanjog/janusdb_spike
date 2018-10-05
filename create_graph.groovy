// CreateGraph.groovy
//
// Simple example of using TinkerGraph with Groovy (outside the Gremlin console)
//
// This example does the following:
//   1. Create an empty TinkerGraph instance
//   2. Create some nodes and vertices
//   3. Run a few queries against the newly created graph

// I have highlighted the places where the Gremlin is slightly different from the
// Gremlin we can use in the Gremlin Console.

import org.apache.tinkerpop.gremlin.process.traversal.*

import org.apache.tinkerpop.gremlin.tinkergraph.structure.*

// Create a new (empty) TinkerGrap
//tg = TinkerGraph.open()
tg = JanusGraphFactory.open('conf/janusgraph-cassandra-es.properties')
JanusGraphFactory.drop(tg) //https://stackoverflow.com/questions/12814305/gremlin-remove-all-vertex

//tg.io(graphson()).readGraph("tinkerpop-modern.json")
//tg.io(graphson()).readGraph("sample_gson.json")
g = tg.traversal()

g.addV("user").property("platform","gp").property("age","40").property("sex","M").as('u1').
    addV("app").property("id","com.foo.bar").as("com.foo.bar").
    addV("app").property("id","1234455").as("1234455").
    addV("device").property("code","Redmi Note 4").as("Redmi Note 4").
    addV("country").property("code","IN").as("IN").
    addV("city").property("code","mumbai").as("mumbai").

    addE("installed_on").from("u1").to("Redmi Note 4").
    addE("installed").from("u1").to("com.foo.bar").
    addE("installed_by").from("com.foo.bar").to("u1").
    addE("installed").from("u1").to("1234455").
    addE("installed_by").from("1234455").to("u1").
    addE("lives_in_country").from("u1").to("IN").
    addE("lives_in_city").from("u1").to("IN").
    addE("has_user").from("IN").to("u1").
    addE("has_user").from("mumbai").to("u1").iterate()

tg.io(graphson()).writeGraph("sample_gson.json")

//// Create a Traversal source object
//g = tg.traversal()
//
//// Add some nodes and vertices - Note the use of "iterate".
//g.addV("airport").property("code","AUS").as("aus").
//  addV("airport").property("code","DFW").as("dfw").
//  addV("airport").property("code","LAX").as("lax").
//  addV("airport").property("code","JFK").as("jfk").
//  addV("airport").property("code","ATL").as("atl").
//  addE("route").from("aus").to("dfw").
//  addE("route").from("aus").to("atl").
//  addE("route").from("atl").to("dfw").
//  addE("route").from("atl").to("jfk").
//  addE("route").from("dfw").to("jfk").
//  addE("route").from("dfw").to("lax").
//  addE("route").from("lax").to("jfk").
//  addE("route").from("lax").to("aus").
//  addE("route").from("lax").to("dfw").iterate()
//
//// Display the vertices created, note we have to use the "T." prefix
//// for label and id as they are not stored as regular strings.
//
//vm = g.V().valueMap(true).toList()
//vm.each {println("${it[T.id]}  ${it.code[0]} ${it[T.label]}")}
//
///* Just for fun!
//for (v in vm)
//{
//  v.keySet().each {print "${v[it]} "}
//  println()
//}
//*/
//

edges = g.E().count().next()
verts = g.V().count().next()

println "The graph has $verts vertices"
println "The graph has $edges edges"
