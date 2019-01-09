#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Can I complete todo?
  A user can complete a todo and completed todos will not show up on the todo list.

  Scenario: Todoアイテムがすべて完了済みのとき、Todoアイテムが表示されない
    Given 一覧ページを表示する
    And Todoアイテムは登録されていない
    When タイトル："Hoge", 説明："Fuga", 期限："2018/10/5"のTodoアイテムを登録済み
    And 1件目の完了ボタンをクリックする
    And タイトル："Fuga", 説明："Hoge", 期限："2018/10/5"のTodoアイテムを登録済み
    And 1件目の完了ボタンをクリックする
    Then 一覧ページが表示される
    And Todoアイテムが0件表示される

  Scenario: 完了済みのTodoアイテムは表示されない
    Given 一覧ページを表示する
    And Todoアイテムは登録されていない
    When タイトル："Fuga", 説明："Hoge", 期限："2018/10/5"のTodoアイテムを登録済み
    And タイトル："Hoge", 説明："Fuga", 期限："2018/10/5"のTodoアイテムを登録済み
    And タイトルが"Fuga"である最初のTodoアイテムを完了する
    Then 一覧ページが表示される
    And Todoアイテムが1件表示される
    And 1件目のタイトルが"Hoge"である

  Scenario: 未完了のTodoを完了すると、一覧に表示されなくなる
    Given 一覧ページを表示する
    And Todoアイテムは登録されていない
    When タイトル："Hoge", 説明："Fuga", 期限："2018/10/5"のTodoアイテムを登録済み
    And 1件目の完了ボタンをクリックする
    Then 一覧ページが表示される
    And Todoアイテムが0件表示される

  Scenario: 内容が同じTodoのうち1つを完了しても他のTodoに影響しない
    Given 一覧ページを表示する
    And Todoアイテムは登録されていない
    When タイトル："Fuga", 説明："Hoge", 期限："2018/10/5"のTodoアイテムを登録済み
    And タイトル："Fuga", 説明："Hoge", 期限："2018/10/5"のTodoアイテムを登録済み
    And タイトルが"Fuga"である最初のTodoアイテムを完了する
    Then 一覧ページが表示される
    And Todoアイテムが1件表示される
    And 1件目のタイトルが"Fuga"である
