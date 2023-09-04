import React, { useState } from 'react';
import { IconContext } from 'react-icons';
import { HiUserGroup, HiUser } from 'react-icons/hi';

const StepThree = () => {
   const [selected, setSelected] = useState();
   const handleCardSelect = (x) => {
      setSelected(x);
      console.log("Card selected is:", x);
   };

   return (
    <>
      <main className="d-flex flex-column align-items-center justify-content-center mt-5 mb-3">
         <h2 className="main-title mb-2">
            How are you planning to use Eden?
         </h2>
         <span className="sub-title mb-3">
            We'll streamline your setup experience accordingly.
         </span>
      </main>
      <section>
         <div className="form-group my-3 step-three-card">
            <div className={`card mx-3 ${selected === 0 ? 'border-primary' : ''}`} onClick={() => handleCardSelect(0)}>
               <div className="card-body d-flex flex-column">
                  <span className="mt-2 mb-4">
                     <IconContext.Provider value={{ size: '26px', color: selected === 0 ? '#6548d8' : 'black' }}>
                        <HiUser/>
                     </IconContext.Provider>
                  </span>
                  <h5 className="mb-2">For myself</h5>
                  <p className="mb-2 font-weight-light">
                     Write better. Think more clearly. Stay organized.
                  </p>
               </div>
            </div>
            <div className={`card mx-3 ${selected === 1 ? 'border-primary' : ''}`} onClick={() => handleCardSelect(1)}>
               <div className="card-body d-flex flex-column pr-3">
                  <span className="mt-2 mb-4">
                     <IconContext.Provider value={{ size: '26px', color: selected === 1 ? '#6548d8' : 'black' }}>
                        <HiUserGroup/>
                     </IconContext.Provider>
                  </span>
                  <h5 className="mb-2">With my team</h5>
                  <p className="mb-2 font-weight-light">
                     Wikis, docs, tasks and projects, all in one place.
                  </p>
               </div>
            </div>
         </div>
      </section>
    </>
  )
}

export default StepThree;