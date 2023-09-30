# 定义编译器和编译选项
CC = javac

# 定义目标文件和依赖关系
SRC =  com/craftinginterpreters/lox
SRCS = $(SRC)/Lox.java $(SRC)/Environment.java $(SRC)/Expr.java \
$(SRC)/Interpreter.java $(SRC)/Parser.java $(SRC)/RuntimeError.java \
$(SRC)/Scanner.java $(SRC)/Stmt.java $(SRC)/Token.java $(SRC)/TokenType.java   # 使用相对路径指定源文件位置
OBJS = $(SRCS:.java=.class)

# 生成中间目标文件
$(OBJS): $(SRCS)
	$(CC) $(SRCS)

.PHONY : clean
# 清理生成的文件
clean:
	rm -f $(SRC)/*.class
