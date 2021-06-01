import hudson.model.*
import com.tikal.jenkins.plugins.multijob.*;

void log(msg) {
    manager.listener.logger.println(msg)
}

threshold = Result.SUCCESS

void aggregate_results() {
    failed = false

    mainJob = manager.build.getProject().getName()
    job = hudson.model.Hudson.instance.getItem(mainJob)

    log "---------------------------------------------------------------------------------------------------------------"
    log "Aggregated status report"
    log "---------------------------------------------------------------------------------------------------------------"

    log("${mainJob}    #${manager.build.getNumber()} - ${manager.build.getResult()}")

    job.getLastBuild().getSubBuilds().each { subBuild->
        subJob = subBuild.getJobName()
        subJobNumber = subBuild.getBuildNumber()
        job = hudson.model.Hudson.instance.getItem(subBuild.getJobName())
        log "${subJob}   #${subJobNumber} - ${job.getLastCompletedBuild().getResult()}"
        log job.getLastCompletedBuild().getLog()

        //println subBuild
        dePhaseJob = hudson.model.Hudson.instance.getItem(subBuild.getJobName())
        dePhaseJobBuild = dePhaseJob.getBuildByNumber(subBuild.getBuildNumber())
        dePhaseJobBuild.getSubBuilds().each { childSubBuild ->
            try {
                log "   ${childSubBuild.jobName}"

                job = hudson.model.Hudson.instance.getItem(childSubBuild.getJobName())
                build = job.getBuildByNumber(childSubBuild.getBuildNumber())

                indent = "  "
                log "${indent} #${build.getNumber()}  - ${build.getResult()}"
                log build.getLog()

                if(!failed && build.getResult().isWorseThan(threshold) ) {
                    failed = true
                }
            } catch (Exception e) {
                log("ERROR: ${e.getMessage()}")
                failed = true
            }
        }
    }

    if(failed) {manager.build.setResult(hudson.model.Result.FAILURE)}
}

try {
    aggregate_results()
} catch(Exception e) {
    log("ERROR: ${e.message}")
    log("ERROR: Failed Status report aggregation")
    manager.build.setResult(hudson.model.Result.FAILURE)
}