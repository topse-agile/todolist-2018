#Author your.email@your.domain.com
#Keywords Summary  
#Feature List of scenarios.
#Scenario Business rule through list of steps with arguments.
#Given Some precondition step
#When Some key actions
#Then To observe outcomes or validation
#And,But To enumerate more Given,When,Then steps
#Scenario Outline List of steps for data-driven as an Examples and <placeholder>
#Examples Container for s table
#Background List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels) To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Can I register todo?
  I feel uneasy without having to do

	Scenario Outline: <feature>条件での登録成功
		Given 一覧ページを表示する
		And Todoアイテムは登録されていない
		And 登録ページを表示する
		When タイトルに"<title>"と入力する
		And 説明に"<detail>"と入力する
		And 期限に"<deadline>"と入力する
		And 登録ボタンをクリックする
		Then 一覧ページが表示される
		And Todoアイテムが<num>件表示される
		And 1件目のタイトルが"<title>"である
		And 1件目の説明が"<detail>"である
		And 1件目の期限が"<exdeadline>"である
		And 1件目の作成日が今日である
	
	Examples:
		| feature | title | detail | deadline | num | exdeadline |
		| 全項目を正常に入力する | Hoge	| Fuga	| 2018/10/25 | 1 | 2018/10/25 |
		| 任意項目を入力しない | Hoge	||| 1 ||
		| ゼロパディングしない日付を入力する | Hoge || 2018/1/1 | 1 | 2018/01/01 |
		| ゼロパディングした日付を入力する | Hoge || 2018/01/01 | 1 | 2018/01/01 |
		| タイトルと説明にマルチバイト文字を含む | ほげHoge | ぴよPiyo | 2018/01/01 | 1 | 2018/01/01 |
