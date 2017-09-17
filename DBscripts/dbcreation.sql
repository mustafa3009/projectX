cqlsh -e "CREATE KEYSPACE IF NOT EXISTS paxos \
	WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 }; \
	DROP TABLE IF EXISTS paxos.tbl-hash; \
	CREATE TABLE paxos.tbl-hash ("msg text PRIMARY KEY, msg_hash text);" 