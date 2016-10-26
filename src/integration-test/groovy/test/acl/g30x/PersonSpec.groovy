package test.acl.g30x

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.acl.AclUtilService
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.acls.domain.BasePermission
import spock.lang.Specification
import test.acl.g30x.auth.User
import org.springframework.security.acls.model.AclCache
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
class PersonSpec extends Specification {

    @Autowired
    AclUtilService aclUtilService

    @Autowired
    AclCache aclCache

    def setup() {
    }

    def cleanup() {
    }

    void "test acl"() {
        given:
        Person person = null
        Person.withNewTransaction{
            User user = new User(username: "admin", password: "admin").save(failOnError: true, flush: true)
            person = new Person().save(flush: true, failOnError: true)
            SpringSecurityUtils.doWithAuth("admin") {
                aclUtilService.addPermission(Person, person.id, 'user', BasePermission.READ)
            }
        }

        expect:
        theFollowingToSucceed(person)==true;
        
    }
    def theFollowingToSucceed(Person person){
       Person.withNewTransaction{
        aclCache.clearCache() // the problem only occurs if the ACL is not yet cached. We therefore clear the cache first to make sure it happens
        println aclUtilService.readAcl(person)
        aclUtilService.deleteAcl(person)
        return true
       }
    }
}
