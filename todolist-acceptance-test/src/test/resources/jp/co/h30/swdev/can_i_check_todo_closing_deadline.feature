#Author: fum.kato.asagi@gmail.com
Feature: Can I check TODOs with deadlines within 3 days?

  Scenario:
    Given 一覧ページを表示する
    And Todoアイテムは登録されていない
    And 登録ページを表示する
    When タイトルに"Hoge"と入力する
    And 説明に"Fuga"と入力する
    And 期限に3日後を入力する
    And 登録ボタンをクリックする
    And 登録リンクをクリックする
    And タイトルに"Fuga"と入力する
    And 説明に"Hoge"と入力する
    And 期限に4日後を入力する
    And 登録ボタンをクリックする
    Then 一覧ページが表示される
    And Todoアイテムが2件表示される
    And 1件目が強調表示である
    And 2件目が強調表示でない

