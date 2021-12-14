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
        Dcl();
        Dcls();
    }

    public void Dcl() {
//     Dcl → floatdcl id | intdcl id
       if (scanner.token == Token.FLOATDCL_TOKEN){
           scanner.match(Token.FLOATDCL_TOKEN);
           scanner.match(Token.ID_TOKEN);
       } else if (scanner.token == Token.INTDCL_TOKEN) {
           scanner.match(Token.INTDCL_TOKEN);
           scanner.match(Token.ID_TOKEN);
       }
    }

    public void Stmts() {
//   Stmts → Stmt Stmts
        Stmt();
        Stmts();
    }

    public void Stmt() {
//  Stmt → id assign Val Expr | print id
        if(scanner.token == Token.ID_TOKEN){
            scanner.match(Token.ID_TOKEN);
            scanner.match(Token.assign_operator);
            Val();
            Expr();
        } else {
            scanner.match(Token.PRINT_TOKEN);
            scanner.match(Token.ID_TOKEN);
        }
    }

    public void Expr(){
//   Expr → plus Val Expr | minus Val Expr | e
        if(scanner.token == Token.plus_operator){
            scanner.match(Token.plus_operator);
            Val();
            Expr();
        } else if (scanner.token == Token.minus_operator){
            scanner.match(Token.minus_operator);
            Val();
            Expr();
        } else {
            scanner.match(Token.blank);
        }
    }

    public void Val(){
//        Val → id | inum | fnum
        if(scanner.token == Token.ID_TOKEN){
            scanner.match(Token.ID_TOKEN);
        } else if (scanner.token == Token.number){
            scanner.match(Token.number);
        }
    }

}
