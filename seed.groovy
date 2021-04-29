folder('CI-Pipelines') {
  displayName('CI Pipelines')
  description('CI Pipelines')
}

def component = ["cart", "catalogue"];

for (i in 0..1) {
  def j=component[i]
  pipelineJob("test-${j}") {
    configure { flowdefinition ->
      flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
        'triggers' {
          'hudson.triggers.SCMTrigger' {
            'spec'('* * * * 1-5')
            'ignorePostCommitHooks'(false)
          }
        }
      }
      flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'('https://github.com/zsdevops01/'+j+'.git')
              'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('*/tags/*')
            }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }
}


pipelineJob('CI-Pipelines/frontend-ci') {
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      'triggers' {
        'hudson.triggers.SCMTrigger' {
          'spec'('* * * * 1-5')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/frontend.git')
            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob('CI-Pipelines/catalogue-ci') {
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      'triggers' {
        'hudson.triggers.SCMTrigger' {
          'spec'('* * * * 1-5')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/catalogue.git')
            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob('CI-Pipelines/cart-ci') {
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      'triggers' {
        'hudson.triggers.SCMTrigger' {
          'spec'('* * * * 1-5')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            'url'('https://github.com/zsdevops01/cart.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob('CI-Pipelines/user-ci') {
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      'triggers' {
        'hudson.triggers.SCMTrigger' {
          'spec'('* * * * 1-5')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/user.git')
            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob('CI-Pipelines/shipping-ci') {
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      'triggers' {
        'hudson.triggers.SCMTrigger' {
          'spec'('* * * * 1-5')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            'url'('https://github.com/zsdevops01/shipping.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob('CI-Pipelines/payment-ci') {
  configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
      'triggers' {
        'hudson.triggers.SCMTrigger' {
          'spec'('* * * * 1-5')
          'ignorePostCommitHooks'(false)
        }
      }
    }
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/payment.git')
            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

