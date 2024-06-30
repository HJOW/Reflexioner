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

public class AReflexSave extends ReflexSave
{
    private static final long serialVersionUID = 3836185875492189922L;
    private Boolean authority_game;
    private Long pause_left;
    public AReflexSave()
    {
        super();
    }
    public AReflexSave(SpaceShip spaceShip, List<Enemy> enemies, List<Missile> missiles, List<Boom> booms, List<Item> items, List<ReflexDecorate> decorates, long difficulty, double difmode, String ver, int continue_left, ReflexScenario scen, boolean autoControl, List<Enemy> ourEnemy, boolean authority_mode, long pause_left)
    {
        super(spaceShip, enemies, missiles, booms, items, decorates, difficulty, difmode, ver, continue_left, scen, autoControl, ourEnemy);
        setAuthority_game(new Boolean(authority_mode));
        setPause_left(new Long(pause_left));
        auth();
    }
    public Boolean getAuthority_game()
    {
        return authority_game;
    }
    public void setAuthority_game(Boolean authority_game)
    {
        this.authority_game = authority_game;
    }
    public Long getPause_left()
    {
        return pause_left;
    }
    public void setPause_left(Long pause_left)
    {
        this.pause_left = pause_left;
    }
}
