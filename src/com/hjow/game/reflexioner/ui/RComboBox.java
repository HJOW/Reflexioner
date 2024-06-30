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
package com.hjow.game.reflexioner.ui;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings("rawtypes")
public class RComboBox extends JComboBox {
	private static final long serialVersionUID = -8615392625817862067L;
    public RComboBox()
    {
    	super();
    }
    @SuppressWarnings("unchecked")
	public RComboBox(ComboBoxModel model)
    {
    	super(model);
    }
    @SuppressWarnings("unchecked")
	public RComboBox(Object[] items)
    {
    	super(items);
    }
    @SuppressWarnings("unchecked")
	public RComboBox(Vector items)
    {
    	super(items);
    }
}
