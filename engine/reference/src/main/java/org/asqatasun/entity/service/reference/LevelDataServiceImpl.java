/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.service.reference;

import java.util.HashMap;
import java.util.Map;
import org.asqatasun.entity.dao.reference.LevelDAO;
import org.asqatasun.entity.reference.Level;
import org.asqatasun.sdk.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service("levelDataService")
public class LevelDataServiceImpl extends AbstractGenericDataService<Level, Long> implements LevelDataService {

    private Map<String, Level> levelMap = new HashMap<String,Level>();
    
    public LevelDataServiceImpl() {
        super();
    }

    @Override
    public Level getByCode(String code) {
        if (levelMap.containsKey(code)) {
            return levelMap.get(code);
        }
        Level level = ((LevelDAO)entityDao).retrieveByCode(code);
        levelMap.put(code, level);
        return level;
    }

}