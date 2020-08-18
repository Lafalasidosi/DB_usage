# Java & PostgreSQL

A chronicle of my self-teaching.

### Aug 15
Struggled for a while with the error *No suitable driver found for jdbc:postgresql://localhost/<database>*;
tutorials and the like always mentioned the [appropriate .jar file](https://jdbc.postgresql.org/), but never where to put it.
Led down one or two rabbit holes about where put .jar files and what Ant is. I solved this problem by putting the downloaded
*postgresql-42.2.14.jar* file in /usr/share/java then specifying that directory in the Libraries section of Project Structure.

The next big issue was [Re: PSQLException: FATAL: password authentication failed for user "XXX"](https://www.postgresql.org/message-id/13628.1366994290@sss.pgh.pa.us).
After poking around in my systems log at /var/log/postgresql/posgresql-12-main.log, in which there were several lines like
```
    2020-08-15 17:18:47.646 PDT [25081] lafa@testdb DETAIL:  User "lafa" has no password assigned.
	  Connection matched pg_hba.conf line 92: "host    all             all             127.0.0.1/32            md5",
```

I found the pg_hba.conf file (not the one I made up in /var/lib/postgresql/12/main, but the one in /etc, where I should have searched first) and edited a bunch
of the "md5" entries to "trusted." Don't ask me how exactly it worked, but it did.

### Aug 17
Lazy Sunday gone and passed, I can now say I know how to tell Java to populate a database.
Spent more time than I care to admit on simple syntax errors.