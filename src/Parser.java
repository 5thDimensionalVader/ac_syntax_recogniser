/*
* The CFG of the AC language is;
*
* Prog → Dcls Stmts $
* Dcls → Dcl Dcls | e
* Dcl → floatdcl id | intdcl id
* Stmts → Stmt Stmts | e
* Stmt → id assign Val Expr | print id
* Expr → plus Val Expr | minus Val Expr | e
* Val → id | inum | fnum
*
* where e is epsilon or empty string or blank
*
* */

public class Parser {
    private Scanner scanner;

    public Parser (Scanner scanner) {this.scanner = scanner;}

    public void run(){
        scanner.getToken();
        Prog();
    }

    public void Prog(){
//   Prog → Dcls Stmts $
        Dcls();
        Stmts();
    }
    public void Dcls(){
//   Dcls → Dcl Dcls | e
        if(scanner.token == Token.floatdcl_token || scanner.token == Token.intdcl_token){
            Dcl();
            Dcls();
        }
    }

    public void Dcl() {
//     Dcl → floatdcl id | intdcl id
       if (scanner.token == Token.floatdcl_token){
           scanner.match(Token.floatdcl_token);
           scanner.match(Token.id_token);
       } else if (scanner.token == Token.intdcl_token) {
           scanner.match(Token.intdcl_token);
           scanner.match(Token.id_token);
       }
    }

    public void Stmts() {
//   Stmts → Stmt Stmts
        if(scanner.token == Token.id_token || scanner.token == Token.print_token) {
            Stmt();
            Stmts();
        }
    }

    public void Stmt() {
//  Stmt → id assign Val Expr | print id
        if(scanner.token == Token.id_token){
            scanner.match(Token.id_token);
            scanner.match(Token.assignop);
            Val();
            Expr();
        } else if (scanner.token == Token.print_token){
            scanner.match(Token.print_token);
            scanner.match(Token.id_token);
        } else {
            System.err.println("ParseError");
        }
    }

    public void Expr(){
//   Expr → plus Val Expr | minus Val Expr | e
        if(scanner.token == Token.plusop){
            scanner.match(Token.plusop);
            Val();
            Expr();
        } else if (scanner.token == Token.minusop){
            scanner.match(Token.minusop);
            Val();
            Expr();
        }
    }

    public void Val(){
//        Val → id | inum | fnum
        if(scanner.token == Token.id_token){
            scanner.match(Token.id_token);
        } else if (scanner.token == Token.inum){
            scanner.match(Token.inum);
        } else {
            scanner.match(Token.fnum);
        }
    }

}
