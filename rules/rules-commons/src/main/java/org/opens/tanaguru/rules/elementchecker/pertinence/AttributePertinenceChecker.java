/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.pertinence;

import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.elementchecker.text.TextEndsWithChecker;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * 
 */
public class AttributePertinenceChecker extends TextPertinenceChecker {

    /* The attr element builder needed to retrieve the attribute to compare with*/
    private TextAttributeOfElementBuilder attrElementBuilder;
    @Override
    public TextElementBuilder getTextElementBuilder() {
        if (attrElementBuilder == null) {
            attrElementBuilder = new TextAttributeOfElementBuilder();
        }
        return attrElementBuilder;
    }

    /**
     * Constructor. 
     * Enables to override the not pertinent solution.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param attrNotPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String extensionsListNameToCompareWith,
            TestSolution attrNotPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(checkEmptiness, 
              attributeNameToCompare, 
              null, // this attribute is about the text blacklist nomenclature 
              attrNotPertinentSolution, 
              notPertinentMessageCode, 
              manualCheckMessage, 
              eeAttributeNameList);
        this.attrElementBuilder.setAttributeName(attributeName);
        
        addExtensionChecker(extensionsListNameToCompareWith);
    }

    /**
     * Constructor. 
     * Enables to override the not pertinent solution.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param attrNotPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String extensionsListNameToCompareWith,
            TestSolution attrNotPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        super(checkEmptiness, 
              attributeNameToCompare, 
              null, // this attribute is about the text blacklist nomenclature 
              attrNotPertinentSolution, 
              notPertinentMessageCode, 
              manualCheckMessage);
        this.attrElementBuilder.setAttributeName(attributeName);
        
        addExtensionChecker(extensionsListNameToCompareWith);
    }
    
    /**
     * Constructor. 
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String extensionsListNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        this(attributeName,
             checkEmptiness, 
             attributeNameToCompare, 
             extensionsListNameToCompareWith,
             TestSolution.FAILED,
             notPertinentMessageCode, 
             manualCheckMessage);
    }
    
    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String extensionsListNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        this(attributeName,
             checkEmptiness, 
             attributeNameToCompare, 
             extensionsListNameToCompareWith,
             TestSolution.FAILED,
             notPertinentMessageCode, 
             manualCheckMessage, 
             eeAttributeNameList);
    }
    
    
    
    

    /**
     * Add a {@link TextEndsWithChecker} to the checker collection
     */
    private void addExtensionChecker(String extensionsListNameToCompareWith) {
        // The second check consists in verifying the element content does not only
        // contains non alphanumerical characters
        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new TextEndsWithChecker(
                            getTextElementBuilder(),
                            extensionsListNameToCompareWith,
                            getNotPertinentSolution(), 
                            notPertinentMessageCode, 
                            getEeAttributeNames()));
        }
    }

}