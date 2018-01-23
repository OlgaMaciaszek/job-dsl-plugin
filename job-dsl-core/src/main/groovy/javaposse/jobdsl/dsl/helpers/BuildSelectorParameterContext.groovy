package javaposse.jobdsl.dsl.helpers

import javaposse.jobdsl.dsl.AbstractContext
import javaposse.jobdsl.dsl.ContextHelper
import javaposse.jobdsl.dsl.DslContext
import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.dsl.helpers.step.CopyArtifactSelectorContext

class BuildSelectorParameterContext extends AbstractContext {

	private static final String DEFAULT_SELECTOR = 'defaultSelector'

	String description
	final CopyArtifactSelectorContext defaultSelectorContext

	protected BuildSelectorParameterContext(JobManagement jobManagement, Item item) {
		super(jobManagement)
		defaultSelectorContext = new CopyArtifactSelectorContext(jobManagement, item)
	}

	void description(String description) {
		this.description = description
	}

	void defaultBuildSelector(@DslContext(CopyArtifactSelectorContext) Closure defaultSelectorClosure) {
		defaultSelectorContext.classifier(DEFAULT_SELECTOR)
		ContextHelper.executeInContext(defaultSelectorClosure, defaultSelectorContext)
	}
}