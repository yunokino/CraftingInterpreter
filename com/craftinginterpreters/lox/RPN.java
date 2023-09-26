package com.craftinginterpreters.lox;

class RPN implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null)
            return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        // 操作数后置而其他并不
        StringBuilder builder = new StringBuilder();
        if (name == "group") {
            builder.append("(").append(name);
        } else {
            builder.append("(");
        }
        for (Expr expr : exprs) {
            if(name == "group")
                builder.append(" ");
            builder.append(expr.accept(this));
            if(name != "group")
                builder.append(" ");
        }
        if (name == "group") {
            builder.append(")");
        } else {
            builder.append(name).append(")");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));
        System.out.println(new RPN().print(expression));
    }
}
