import React, { useState } from 'react';
import Stepper from "@material-ui/core/Stepper";
import Step from "@material-ui/core/Step";
import StepButton from "@material-ui/core/StepButton";
import StepOne from './Steps/StepOne';
import StepTwo from './Steps/StepTwo';
import StepThree from './Steps/StepThree';
import StepFour from './Steps/StepFour';
import edenIcon from '../icons/eden.png';

const getSteps = () => {
   return ["", "", "", ""];
};

const getStepContent = (step) => {
   switch (step) {
      case 0:
         return <StepOne />;
      case 1:
         return <StepTwo />;
      case 2:
         return <StepThree />;
      case 3:
         return <StepFour />;
      default:
         return "unknown step";
   }
};



const Home = () => {
   const [activeStep, setActiveStep] = useState(0);
   const [completed, setCompleted] = useState({});
   const steps = getSteps();
   const totalSteps = () => {
      return steps.length;
   };
   const completedSteps = () => {
      return Object.keys(completed).length;
   };
   const isLastStep = () => {
      return activeStep === totalSteps() - 1;
   };
   const allStepsCompleted = () => {
      return completedSteps() === totalSteps();
   };
   const handleNext = () => {
      const newActiveStep =
         isLastStep() && !allStepsCompleted()
            ? steps.findIndex((step, i) => !(i in completed))
            : activeStep + 1;
      completed[activeStep] = true;
      setCompleted(completed);
      setActiveStep(newActiveStep);

   };
   const handleStep = step => () => {
      setActiveStep(step);
   };

   return (
      <div className="container-md d-flex flex-column justify-content-center align-items-center layout">
         <section className="row mt-1 mb-4">
            <img src={edenIcon} alt="logo" />
         </section>
         <section className="row my-4">
            <Stepper alternativeLabel activeStep={activeStep}>
               {steps.map((label, index) => (
                  <Step key={label}>
                     <StepButton
                        onClick={handleStep(index)}
                        completed={completed[index]}
                     >
                        {label}
                     </StepButton>
                  </Step>
               ))}
            </Stepper>
            <div>
               {getStepContent(activeStep)}
               {
                  (
                     <div>
                        <button className="button-accent border-0 rounded" onClick={handleNext}>
                           {activeStep !== 3 ? 'Create Workspace' : 'Launch Eden'}
                        </button>
                     </div>
                  )
               }
            </div>
         </section>
      </div>
   )
}

export default Home;