## Preparing

在“曲谱”文件夹中创建.txt文件并按照如下格式编辑你想要转换的曲谱

## Getting Started

在当前路径下打开cmd.exe，输入java -jar transfer.jar

## Format

#### 曲谱支持的输入

+ 不带点的0-7：直接输入数字，如：0

+ 上加点（高音）的1-7：数字+点的个数，如：1..

+ 下加点（低音）的1-7：点的个数+数字，如:..1
+ 小节线：|
+ 时值延长线：-
+ 升降号：#和b，打在数字之前，如：#.1
+ 每个音符之间隔一个空格

#### 映射表支持的输入

pairs in the format of  `note key`

example: #.1 W
