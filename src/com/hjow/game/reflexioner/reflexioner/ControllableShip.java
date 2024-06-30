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
package com.hjow.game.reflexioner.reflexioner;

import java.util.List;

public interface ControllableShip
{
    public void control_up();
    public void control_down();
    public void control_left();
    public void control_right();
    public void control_break();
    public void control_w();
    public void control_a();
    public void control_s();
    public void control_d();
    public void control_1();
    public void control_2();
    public void control_3();
    public void control(int k);
    public void control_auto(List<Enemy> enemyList, List<Missile> missileList);
}
