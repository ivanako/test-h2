library 'global-alm-pipeline-library'
mavenDevOpsPipeline (
   masterBranch: 'master',
   integrationBranch: 'develop',
   agent: 'jdk11',
   deploy_cert: [
      environment: 'CERT',
   ],
   deploy_pre: [
      environment: 'PRE',
      approve: '' 
   ],
   deploy_pro: [
      environment: 'PRO',
      approve: ''
   ]
)
