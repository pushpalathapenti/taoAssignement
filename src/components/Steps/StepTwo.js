const StepTwo = () => {
    return (
      <>
        <main className="d-flex flex-column align-items-center justify-content-center mt-5 mb-3">
           <h2 className="main-title mb-2">
              Let's set up a home for all your work
           </h2>
           <span className="sub-title mb-3">
              You can always create another workspace later.
           </span>
        </main>
        <section>
           <div className="form-group mb-3">
              <label htmlFor="fullName" className="input-label mb-2">Workspace Name</label>
              <input className="form-control input-field" type="text" placeholder="Eden" />
           </div>
           <div className="form-group my-3">
              <label htmlFor="displayName" className="input-label mb-2">
                Workspace URL <span className="opacity-50">(optional)</span>
              </label>
              <div className="input-group">
                <div className="input-group-prepend">
                  <span className="input-group-text opacity-80">www.eden.com/</span>
                </div>
                <input type="text" className="form-control" id="basic-url"/>
              </div>
           </div>
        </section>
      </>
    )
  }
  
  export default StepTwo