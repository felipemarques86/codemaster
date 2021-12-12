import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestConfigActivityComponent } from './test-config-activity.component';

describe('TestConfigActivityComponent', () => {
  let component: TestConfigActivityComponent;
  let fixture: ComponentFixture<TestConfigActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestConfigActivityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestConfigActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
