grails.plugin.springsecurity.userLookup.userDomainClassName = 'test.acl.g30x.auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'test.acl.g30x.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'test.acl.g30x.auth.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
    [pattern: '/', access:                     ['permitAll']],
    [pattern: '/error', access:                ['permitAll']],
    [pattern: '/index', access:                ['permitAll']],
    [pattern: '/index.gsp', access:            ['permitAll']],
    [pattern: '/shutdown', access:             ['permitAll']],
    [pattern: '/assets/**', access:            ['permitAll']],
    [pattern: '/**/js/**', access:             ['permitAll']],
    [pattern: '/**/css/**', access:            ['permitAll']],
    [pattern: '/**/images/**', access:         ['permitAll']],
    [pattern: '/**/favicon.ico', access:       ['permitAll']],
    [pattern: '/j_spring_security_switch_user', access: ['ROLE_SUPERVISOR']]
]

grails.plugin.springsecurity.useSwitchUserFilter = true
grails.plugin.springsecurity.switchUser.targetUrl = '/secure/'
grails.plugin.springsecurity.adh.errorPage = null // to trigger a 403