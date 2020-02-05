package viewStatement.ui.controller;

import org.springframework.web.bind.annotation.*;
import viewStatement.ui.model.ViewStatementRequest;
import viewStatement.ui.model.ViewStatementResponse;

@RestController
@RequestMapping("/user")
public class ViewStatement {

    @PostMapping("/viewStatement")
    public <ViewStatementResponse[]> viewStatement(@RequestBody ViewStatementRequest viewStatementRequest){

        return null;
    }
}
