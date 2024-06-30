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
package com.hjow.game.reflexioner.mainClasses;
import com.hjow.game.reflexioner.setting.Setting;

public interface Controllable
{
    public void stop_game();
    public void start_game();
    public void exit();
    public void takeCard();
    public void payCard(int blockNumber, int pay_card_index);
    public void selectAuthorizeCheckbox();
    public void selectPlayerSetting(int index, int value);
    public Setting getSetting();
}
