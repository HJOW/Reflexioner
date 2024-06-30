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
package com.hjow.game.reflexioner.pack;

import java.util.ArrayList;
import java.util.List;

public class InstalledPack
{
    @SuppressWarnings("unchecked")
    public static List<Pack> getPacks() {
        List<String> packClasses = new ArrayList<String>();
        packClasses.add("com.hjow.game.reflexioner.pack.Pack1");
        
        List<Pack> packs = new ArrayList<Pack>();
        packs.addAll(new SecuredDist().getPacks());
        
        for(String cls : packClasses)
        {
            try
            {
                Class<? extends Pack> packClass = (Class<? extends Pack>) Class.forName(cls);
                Pack pack = packClass.newInstance();
                if(packs.contains(pack)) packs.add(pack);
            }
            catch(ClassNotFoundException ex) 
            {}
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return packs;
    }
}
