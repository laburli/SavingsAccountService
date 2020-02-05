package creditdebitsum.Controller;

import creditdebitsum.Model.Request;
import creditdebitsum.Model.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class controller {

    @RestController
    @RequestMapping("/user")
    public class ViewCreditDebitSum {
        @PostMapping("/viewCreditDebitSum")
        public Response[] viewStatement(@RequestBody Request Response){
            return null;
        }
    }






}
