
const StepOne = () => {
    return (
      <>
        <main className="d-flex flex-column align-items-center justify-content-center mt-5 mb-3">
           <h2 className="main-title mb-2">
              Welcome! First things first...
           </h2>
           <span className="sub-title mb-3">
              You can always change them later.
           </span>
        </main>
        <section>
           <div className="form-group mb-3">
              <label htmlFor="fullName" className="input-label mb-2">Full Name</label>
              <input className="form-control input-field" type="text" placeholder="Steve Jobs" />
           </div>
           <div className="form-group my-3">
              <label htmlFor="displayName" className="input-label mb-2">Display Name</label>
              <input className="form-control input-field" type="text" placeholder="Steve" />
           </div>
        </section>
      </>
    )
  }
  
  export default StepOne;