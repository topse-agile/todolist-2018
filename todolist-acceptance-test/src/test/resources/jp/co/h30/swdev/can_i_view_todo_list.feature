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

  Scenario: 未完了のTODOアイテムが0件
    Given Todoアイテムは登録されていない
    When 一覧ページを表示する
    Then Todoアイテムが0件表示される

  Scenario: 未完了のTODOアイテムが1件
    Given Todoアイテムは登録されていない
    And 登録ページを表示する
    When タイトルに"Hoge"と入力する
    And 説明に"Fuga"と入力する
    And 期限に"2018/10/5"と入力する
    And 登録ボタンをクリックする
    Then 一覧ページが表示される
    And Todoアイテムが1件表示される

  Scenario: 未完了のTODOアイテムが2件
    Given Todoアイテムは登録されていない
    And 登録ページを表示する
    When タイトルに"Hoge"と入力する
    And 説明に"Fuga"と入力する
    And 期限に"2018/10/6"と入力する
    And 登録ボタンをクリックする
    And 登録リンクをクリックする
    And タイトルに"Fuga"と入力する
    And 説明に"Hoge"と入力する
    And 期限に"2018/10/5"と入力する
    And 登録ボタンをクリックする
    Then 一覧ページが表示される
    And Todoアイテムが2件表示される
    And 1件目のタイトルが"Hoge"である
    And 2件目のタイトルが"Fuga"である

  Scenario: 登録画面に遷移
    Given 一覧ページを表示する
    When 登録リンクをクリックする
    Then 登録ページが表示される