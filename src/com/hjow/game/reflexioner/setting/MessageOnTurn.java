/*
Copyright 2024 HJOW (hujinone22@naver.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.hjow.game.reflexioner.setting;

import java.io.Serializable;

public class MessageOnTurn implements Serializable
{
    private static final long serialVersionUID = -3728153706437813480L;
    private String contents = "";
    private String kor_contents;
    private Integer turn;
    
    public MessageOnTurn()
    {
        turn = new Integer(0);
    }
    public MessageOnTurn(String contents, int turn)
    {
        this.contents = new String(contents);
        this.turn = new Integer(turn);
    }
    public MessageOnTurn(String contents, String korc, int turn)
    {
        this.contents = new String(contents);
        this.kor_contents = new String(korc);
        this.turn = new Integer(turn);
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }
    public Integer getTurn()
    {
        return turn;
    }
    public void setTurn(Integer turn)
    {
        this.turn = turn;
    }
    public String getKor_contents()
    {
        return kor_contents;
    }
    public void setKor_contents(String kor_contents)
    {
        this.kor_contents = kor_contents;
    }
    public MessageOnTurn clone()
    {
        if(kor_contents == null)
            return new MessageOnTurn(contents, turn.intValue());
        else
            return new MessageOnTurn(contents, kor_contents, turn.intValue());
    }
}
