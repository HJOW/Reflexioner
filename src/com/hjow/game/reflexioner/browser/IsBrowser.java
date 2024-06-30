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
package com.hjow.game.reflexioner.browser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.event.HyperlinkListener;

public interface IsBrowser
{
    public Component getComponent();
    public void setPage(String str) throws IOException;
    public void addHyperlinkListener(HyperlinkListener listener);
    public void setBackground(Color color);
    public void setForeground(Color color);
    public void setFont(Font font);
    public void setText(String str);
}
