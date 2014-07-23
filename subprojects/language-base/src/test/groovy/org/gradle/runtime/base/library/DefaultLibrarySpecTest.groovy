/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.runtime.base.library

import org.gradle.runtime.base.ComponentSpecIdentifier
import spock.lang.Specification

class DefaultLibrarySpecTest extends Specification {
    def libraryId = Mock(ComponentSpecIdentifier)

    def "library has name and path"() {
        def library = new DefaultLibrarySpec(libraryId)

        when:
        _ * libraryId.name >> "jvm-lib"
        _ * libraryId.projectPath >> ":project-path"

        then:
        library.name == "jvm-lib"
        library.projectPath == ":project-path"
        library.displayName == "DefaultLibrarySpec 'jvm-lib'"
    }

    def "has sensible display name"() {
        def library = new MySampleLibrary(libraryId)

        when:
        _ * libraryId.name >> "jvm-lib"

        then:
        library.displayName == "MySampleLibrary 'jvm-lib'"
    }

    class MySampleLibrary extends DefaultLibrarySpec {
        MySampleLibrary(ComponentSpecIdentifier identifier) {
            super(identifier)
        }
    }
}