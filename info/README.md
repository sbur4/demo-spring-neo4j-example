get http://localhost:8080/book/id
post http://localhost:8080/book/name
put http://localhost:8080/book/id&name
delete http://localhost:8080/book/id

docker=> neo4j:latest
port:7474
port:7687

http://localhost:7474/browser
CREATE (database:Database {name:"neo4j_demo"})

neo4j/neo4j = > change password

Match(n) return n
MATCH (n) DETACH DELETE n