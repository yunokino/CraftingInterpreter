# CraftingInterpreter
《CraftingInterpreter》做的编译器代码

前端 : 词法分析、语法分析=>AST(抽象语法树)
中端 : 静态分析、生成中间代码(IR)、优化
后端 : 生成最终代码(虚拟机或特定架构的机器语言或其他高级语言)

## 词法分析
<!-- 可以正则表达式将字符分组为词素 -->
词素只是源代码的子字符串(类似单词啦)
生成词素与类型的对应

## 语法分析
元编程，AST
