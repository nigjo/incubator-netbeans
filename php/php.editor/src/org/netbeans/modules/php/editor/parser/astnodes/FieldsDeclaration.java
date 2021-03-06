/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.php.editor.parser.astnodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a fields declaration
 * <pre>e.g.<pre> var $a, $b;
 * public $a = 3;
 * final private static $var;
 */
public class FieldsDeclaration extends BodyDeclaration {

    private final ArrayList<SingleFieldDeclaration> fields = new ArrayList<>();

    public FieldsDeclaration(int start, int end, int modifier, List variablesAndDefaults) {
        super(start, end, modifier);

        if (variablesAndDefaults == null || variablesAndDefaults.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (Iterator iter = variablesAndDefaults.iterator(); iter.hasNext();) {
            final Object next = iter.next();
            if (next instanceof SingleFieldDeclaration) {
                this.fields.add((SingleFieldDeclaration) next);
            } else {
                ASTNode[] element = (ASTNode[]) next;
                SingleFieldDeclaration field = createField((Variable) element[0], (Expression) element[1]);
                this.fields.add(field);
            }
        }
    }

    private SingleFieldDeclaration createField(Variable name, Expression value) {
        int start = name.getStartOffset();
        int end = value == null ? name.getEndOffset() : value.getEndOffset();
        final SingleFieldDeclaration result = new SingleFieldDeclaration(start, end, name, value);
        return result;
    }

    /**
     * The list of single fields that are declared
     *
     * @return List of single fields
     */
    public List<SingleFieldDeclaration> getFields() {
        return this.fields;
    }

    public Expression[] getInitialValues() {
        Expression[] result = new Expression[this.fields.size()];
        int i = 0;
        for (SingleFieldDeclaration field : this.fields) {
            result[i++] = field.getValue();
        }
        return result;
    }

    public Variable[] getVariableNames() {
        Variable[] result = new Variable[this.fields.size()];
        int i = 0;
        for (SingleFieldDeclaration field : this.fields) {
            result[i++] = field.getName();
        }
        return result;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SingleFieldDeclaration singleFieldDeclaration : getFields()) {
            sb.append(singleFieldDeclaration).append(" "); //NOI18N
        }
        return sb.toString();
    }

}
