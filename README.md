# JavaFX & PostgreSQL

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

### Aug 19
Two days of frustration gone and passed, I've finally gotten JavaFX to work; I'd keep getting awful *javafx doesn't exist* errors. 
Somehow fixed it after messing about with it. Feeling like I'm falling behind.

**Later that day:** A few searches later and the code can print lines from a single table to the terminal.
Next on the agenda is to make use of the TableView class and output to a whole window.

### Aug 21
I now know how to get this thing to do some operations to a table.
Found out that trying something like 
```
public void start(Stage primaryStage) throws Exception {
    try(Connection c = connectToDatabaseMethod(url, usr, passwd);
    Statement s = c.createStatement();){
/* etc. */
    s.executeUpdate(...);
```
always ends in an exception. Researching the topic. 
What I managed here works for now, anyway.

### August 22
Learned how to put table entries into a TableView object:
how *I* did it, at least, is I "translated" ResultSet elements into objects of class Entry written to supplement the main app.
Next step is to refactor the code to do what my project is *actually* going to be:
a blood glucose data entry app for my roommates. Sure, they could be taught Excel with some effort, but this is more fun.

### August 23
So far today just made the code display and connect to a database which looks more like the Norther Health form I'm referencing.
Next step is to add functionality for adding and/or deleting rows.

#### Later that day
Began reviewing creation of additional windows and built foundation on which to implement the addition of a new line of glucose data.
I can see that updating the table one element of a row at a time if the user wishes would probably be best at some point.

### August 24
Picking up the pace; basic adding and removing capabilites added. Dependent on specific formatting for the moment.
Can forsee some things I'd want to fix: table view of database'll need "pages" at some point; add/delete windows need to be prettier;
AddRow TextArea should either be smaller or feature word wrap somehow; deleting a row should be as easy as highlighing row to delete
or opening up the delete dialogue if no rows selected.

Next step is to either tackle some of the above issues or add more functionality, like displaying an average over some range of dates.
