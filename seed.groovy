folder('CI-Pipelines') {
  displayName('CI Pipelines')
  description('CI Pipelines')
}

def component = ["cart", "catalogue","user","payment","shipping","frontend"];

def count=(component.size()-1)
for (i in 0..count) {
  def j=component[i]
  pipelineJob("CI-Pipelines/${j}-ci") {
    configure { flowdefinition ->
      flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
        'triggers' {
          'hudson.triggers.SCMTrigger' {
            'spec'('*/2 * * * 1-5')
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
        'scriptPath'('Jenkinsfile-Docker')
        'lightweight'(true)
      }
    }
  }
}

pipelineJob("Deployment Pipeline") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/jenkins.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-Deployment')
      'lightweight'(true)
    }
  }
}

folder('Kubernetes') {
  displayName('Kubernetes')
  description('Kubernetes')
}

pipelineJob("Kubernetes/Databases") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/jenkins.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-k8s-databases')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/MongoDB-Schema") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/mongodb.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/MySQL-Schema") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/mysql.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/Frontend") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/frontend.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-K8S')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/Catalogue") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/catalogue.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-K8S')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/Cart") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/cart.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-K8S')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/User") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/user.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-K8S')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/Shipping") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/shipping.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-K8S')
      'lightweight'(true)
    }
  }
}

pipelineJob("Kubernetes/Payment") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/payment.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-K8S')
      'lightweight'(true)
    }
  }
}

pipelineJob("CI-CD-Test") {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/zsdevops01/frontend.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-cicd')
      'lightweight'(true)
    }
  }
}
