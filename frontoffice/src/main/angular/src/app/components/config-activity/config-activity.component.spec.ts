import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigActivityComponent } from './config-activity.component';

describe('ConfigActivityComponent', () => {
  let component: ConfigActivityComponent;
  let fixture: ComponentFixture<ConfigActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfigActivityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
